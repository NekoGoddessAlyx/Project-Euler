use crate::utils::is_palindrome;

pub fn problem0004() -> String {
    let mut largest: u64 = 0;
    for a in 100..=999 {
        for b in 100..=999 {
            let c = a * b;
            if is_palindrome(c) && c > largest { largest = c; }
        }
    }
    largest.to_string()
}