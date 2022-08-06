use crate::utils::collatz_sequence_length;

pub fn problem0014() -> String {
    (1..=1_000_000_u64)
        .max_by_key(|n| collatz_sequence_length(*n))
        .unwrap()
        .to_string()
}