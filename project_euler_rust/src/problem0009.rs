pub fn problem0009() -> String {
    for a in 1..=998 {
        for b in a..=(999 - a) {
            let c = 1000 - a - b;
            if a * a + b * b == c * c { return (a * b * c).to_string() }
        }
    }
    0.to_string()
}