// use crate::register;
//
// #[ctor::ctor]
// fn register_problem0001() { register(1, problem0001); }

pub fn problem0001() -> String {
    let mut sum: u64 = 0;

    for i in 1..1000 {
        if i % 3 == 0 || i % 5 == 0 {
            sum += i;
        }
    }

    sum.to_string()
}

