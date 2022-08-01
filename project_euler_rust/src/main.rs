use std::collections::HashMap;
use crate::problem0001::problem0001;
use crate::problem0002::problem0002;
use crate::problem0003::problem0003;

mod problem0001;
mod problem0002;
mod utils;
mod problem0003;

fn main() {
    let mut problems: HashMap<usize, fn() -> String> = HashMap::new();
    problems.insert(1, problem0001);
    problems.insert(2, problem0002);
    problems.insert(3, problem0003);

    println!("Project Euler (rust edition)");
    println!("{} problem(s) have been solved.", problems.len());
    println!();

    for num in 1..=problems.len() {
        let solver = match problems.get(&num) {
            None => continue,
            Some(solver) => solver
        };
        println!("Problem {} (https://projecteuler.net/problem={}): {}", num, num, solver())
    }
}

// fn main() {
//     SOLVERS.with(|s| {
//         let s = s.borrow();
//         println!("Project Euler (rust edition)");
//         println!("{} problem(s) have been solved.", s.len());
//         // println!();
//
//         let iter = s.iter();
//         for problem in iter {
//             let num = problem.problem_number;
//             let solver = problem.solver;
//             println!("Problem {} (https://projecteuler.net/problem={}): {}", num, num, solver())
//         }
//     });
// }

// struct Problem {
//     problem_number: u32,
//     solver: fn() -> String,
// }
//
// fn register(problem_number: u32, solver: fn() -> String) {
//     SOLVERS.with_borrow_mut(|s| {
//         let index = if problem_number as usize > s.len() { s.len() } else { problem_number as usize };
//         s.insert(index, Problem { problem_number, solver });
//     });
// }
//
// thread_local! {
//     static SOLVERS: RefCell<Vec<Problem>> = RefCell::new(Vec::new());
// }