use crate::utils::fibonacci;

pub fn problem0002() -> String {
    let mut f = fibonacci();
    f.next();
    f.take_while(|n| { *n < 4_000_000 })
        .filter(|n| { *n % 2 == 0 })
        .sum::<u64>()
        .to_string()
}