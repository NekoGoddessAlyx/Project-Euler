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