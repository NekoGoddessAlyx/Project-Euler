use std::collections::HashMap;
use crate::problem0001::problem0001;

mod problem0001;

fn main() {
    let mut problems: HashMap<u32, fn() -> String> = HashMap::new();
    problems.insert(1, problem0001);

    println!("Project Euler (rust edition)");
    println!("{} problem(s) have been solved.", problems.len());
    println!();

    let iter = problems.iter();
    for (num, solver) in iter {
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