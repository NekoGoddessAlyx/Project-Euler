pub fn problem0006() -> String {
    let sum_of_squares: i64 = (1..=100).map(|n| n * n).sum();
    let square_of_sum: i64 = (1..=100).sum::<i64>().pow(2);
    (sum_of_squares - square_of_sum).abs().to_string()
}