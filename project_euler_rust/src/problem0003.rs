use crate::utils::get_prime_factors;

pub fn problem0003() -> String {
    get_prime_factors(600_851_475_143)
        .keys()
        .max()
        .unwrap()
        .to_string()
}