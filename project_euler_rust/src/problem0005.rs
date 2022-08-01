use crate::utils::lcm;

pub fn problem0005() -> String {
    lcm(&mut (2_u64..=20_u64)).to_string()
}