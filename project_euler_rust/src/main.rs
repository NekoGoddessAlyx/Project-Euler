#![feature(local_key_cell_methods)]

use std::cell::RefCell;

struct Problem {
    problem_number: u32,
    solver: fn() -> String,
}

fn register(problem_number: u32, solver: fn() -> String) {
    SOLVERS.with_borrow_mut(|s| {
        let index = if problem_number as usize > s.len() { s.len() } else { problem_number as usize };
        s.insert(index, Problem { problem_number, solver });
    });
}

thread_local! {
    static SOLVERS: RefCell<Vec<Problem>> = RefCell::new(Vec::new());
}

fn main() {
    SOLVERS.with(|s| {
        let s = s.borrow();
        println!("Project Euler (rust edition)");
        println!("{} problem(s) have been solved.", s.len());
        // println!();

        let iter = s.iter();
        for problem in iter {
            let num = problem.problem_number;
            let solver = problem.solver;
            println!("Problem {} (https://projecteuler.net/problem={}): {}", num, num, solver())
        }
    });
}