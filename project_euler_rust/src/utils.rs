use std::collections::HashMap;

// fibonacci generator

pub struct Fibonacci(u64, u64);

impl Iterator for Fibonacci {
    type Item = u64;

    fn next(&mut self) -> Option<Self::Item> {
        let result = self.0;
        self.0 = self.1;
        self.1 += result;
        Some(result)
    }
}

pub fn fibonacci() -> Fibonacci {
    Fibonacci(0, 1)
}

// prime factors

pub fn get_prime_factors(n: u64) -> HashMap<u64, u64> {
    let mut n = n;
    let mut factors: HashMap<u64, u64> = HashMap::new();

    while n % 2 == 0 {
        match factors.get_key_value(&2) {
            None => factors.insert(2, 1),
            Some(x) => factors.insert(2, *x.1 + 1)
        };
        n /= 2;
    }

    let end = f64::sqrt(n as f64).ceil() as u64;
    let mut factor: u64 = 3;

    while factor <= end {
        while n % factor == 0 {
            match factors.get_key_value(&factor) {
                None => factors.insert(factor, 1),
                Some(x) => factors.insert(factor, *x.1 + 1)
            };
            n /= factor;
        }
        factor += 2;
    }

    if n > 2 {
        match factors.get_key_value(&n) {
            None => factors.insert(n, 1),
            Some(x) => factors.insert(n, *x.1 + 1)
        };
    }

    factors
}