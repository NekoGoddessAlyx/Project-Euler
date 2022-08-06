use crate::utils::prime_generator;

pub fn problem0007() -> String {
    prime_generator().take(10_001).last().unwrap().to_string()
}