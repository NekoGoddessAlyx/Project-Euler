pub fn problem0016() -> String {
    num_bigint::BigUint::from(2u8)
        .pow(1000)
        .to_string()
        .bytes()
        .fold(0u128, |acc, d| acc + (d - '0' as u8) as u128)
        .to_string()
}