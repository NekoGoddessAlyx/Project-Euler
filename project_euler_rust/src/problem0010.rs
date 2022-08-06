use crate::utils::prime_generator;

pub fn problem0010() -> String {
    prime_generator().take_while(|n| { *n < 2_000_000 }).sum::<u64>().to_string()
}