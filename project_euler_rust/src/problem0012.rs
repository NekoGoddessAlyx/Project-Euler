use crate::utils::{get_number_of_divisors, triangle_number_generator};

pub fn problem0012() -> String {
    triangle_number_generator()
        .find(|n| { get_number_of_divisors(*n) > 500 })
        .unwrap()
        .to_string()
}