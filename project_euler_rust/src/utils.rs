use std::cmp::max;
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

// lcm (least common multiple)

pub fn lcm(numbers: &mut impl Iterator<Item = u64>) -> u64 {
    let mut factors = HashMap::new();

    for n in numbers {
        let n_factors = get_prime_factors(n);
        for (k, v) in n_factors {
            match factors.get_key_value(&k) {
                None => factors.insert(k, v),
                Some(x) => factors.insert(k, max(*x.1, v))
            };
        }
    }

    let mut n = 1_u64;
    for (k, v) in factors { n *= k.pow(v as u32); }
    n
}

// digits

pub fn get_digit(n: u64, i: u32) -> u32 {
    let d = n / 10_u64.pow(i);
    (d % 10) as u32
}

pub fn num_digits(n: u64) -> u32 {
    if n == 0 { return 0; }
    if n < 10 { return 1; }
    if n < 100 { return 2; }
    if n < 1000 { return 3; }
    if n < 10000 { return 4; }
    if n < 100000 { return 5; }
    if n < 1000000 { return 6; }

    let mut num_digits: u32 = 6;
    let mut n = n / 1000000;
    while n > 9 {
        n /= 10;
        num_digits += 1;
    }

    num_digits
}

// palindrome

pub fn is_palindrome(n: u64) -> bool {
    let num_digits = num_digits(n);
    for i in 0..num_digits {
        if get_digit(n, i) != get_digit(n, num_digits - 1 - i) { return false; }
    }
    true
}