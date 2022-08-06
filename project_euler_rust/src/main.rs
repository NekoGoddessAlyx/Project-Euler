use std::collections::HashMap;
use std::fmt::{Display, Formatter};
use std::fs;
use std::hash::{Hash, Hasher};
use std::time::Instant;

use crate::problem0001::problem0001;
use crate::problem0002::problem0002;
use crate::problem0003::problem0003;
use crate::problem0004::problem0004;
use crate::problem0005::problem0005;
use crate::problem0006::problem0006;
use crate::problem0007::problem0007;
use crate::problem0008::problem0008;
use crate::problem0009::problem0009;

mod utils;
mod problem0001;
mod problem0002;
mod problem0003;
mod problem0004;
mod problem0005;
mod problem0006;
mod problem0007;
mod problem0008;
mod problem0009;

fn problems() -> HashMap<usize, fn() -> String> {
    let mut problems: HashMap<usize, fn() -> String> = HashMap::new();
    problems.insert(1, problem0001);
    problems.insert(2, problem0002);
    problems.insert(3, problem0003);
    problems.insert(4, problem0004);
    problems.insert(5, problem0005);
    problems.insert(6, problem0006);
    problems.insert(7, problem0007);
    problems.insert(8, problem0008);
    problems.insert(9, problem0009);
    problems
}

fn parse_solutions_file(csv: &str) -> HashMap<usize, &str> {
    let mut solutions: HashMap<usize, &str> = HashMap::new();
    for line in csv.lines() {
        let csv: Vec<&str> = line.split(',').collect();

        let n = csv.get(0);
        if n.is_none() { continue; }
        let n = n.unwrap().trim();
        let n = n[1..n.len() - 1].parse();
        if n.is_err() { continue; }
        let n = n.unwrap();

        let s = csv.get(1);
        if s.is_none() { continue; }
        let s = s.unwrap().trim();
        if s.len() < 2 { continue; }
        let s = &s[1..s.len() - 1];

        solutions.insert(n, s);
    }
    solutions
}

enum ResultStatus {
    Unknown,
    Pass,
    Fail,
}

impl Clone for ResultStatus {
    fn clone(&self) -> Self {
        match self {
            ResultStatus::Unknown => ResultStatus::Unknown,
            ResultStatus::Pass =>  ResultStatus::Pass,
            ResultStatus::Fail => ResultStatus::Fail
        }
    }
}

impl Copy for ResultStatus {}

impl Hash for ResultStatus {
    fn hash<H: Hasher>(&self, state: &mut H) {
        match self {
            ResultStatus::Unknown => state.write_u8(0),
            ResultStatus::Pass => state.write_u8(1),
            ResultStatus::Fail => state.write_u8(2),
        }
    }
}

impl PartialEq<Self> for ResultStatus {
    fn eq(&self, other: &Self) -> bool {
        match (self, other) {
            (ResultStatus::Unknown, ResultStatus::Unknown) => true,
            (ResultStatus::Pass, ResultStatus::Pass) => true,
            (ResultStatus::Fail, ResultStatus::Fail) => true,
            _ => false
        }
    }
}

impl Eq for ResultStatus {}

impl Display for ResultStatus {
    fn fmt(&self, f: &mut Formatter<'_>) -> std::fmt::Result {
        match self {
            ResultStatus::Unknown => f.write_fmt(format_args!("Unknown")),
            ResultStatus::Pass => f.write_fmt(format_args!("Pass")),
            ResultStatus::Fail => f.write_fmt(format_args!("Fail"))
        }
    }
}

fn compare_result(expected: Option<&&str>, actual: &str) -> ResultStatus {
    if expected.is_none() { return ResultStatus::Unknown; }
    if expected.unwrap().eq(&actual) { ResultStatus::Pass } else { ResultStatus::Fail }
}

fn main() {
    let solutions = fs::read_to_string("../solutions.csv").unwrap_or_default();
    let solutions = parse_solutions_file(&solutions);
    let problems = problems();

    println!("Project Euler (rust edition)");
    println!("{} problem(s) have been solved.", problems.len());
    println!();

    let mut results: HashMap<ResultStatus, usize> = HashMap::new();
    results.insert(ResultStatus::Unknown, 0);
    results.insert(ResultStatus::Pass, 0);
    results.insert(ResultStatus::Fail, 0);

    let largest = problems.keys().max().unwrap_or(&0);
    for num in 1..=*largest {
        let solver = match problems.get(&num) {
            None => continue,
            Some(solver) => solver
        };
        let now = Instant::now();
        let actual_answer = solver();
        let elapsed = now.elapsed();
        let expected_answer = solutions.get(&num);
        let result_status = compare_result(expected_answer, &actual_answer);
        results.insert(result_status, results.get(&result_status).unwrap() + 1);

        println!("Problem {} (https://projecteuler.net/problem={}): {} in {:.2?} ({})", num, num, actual_answer, elapsed, result_status)
    }

    println!();
    let num_passed = results.get(&ResultStatus::Pass).unwrap();
    if *num_passed != 0 { println!("{} problems passed.", num_passed); }
    let num_failed = results.get(&ResultStatus::Fail).unwrap();
    if *num_failed != 0 { println!("{} problems failed.", num_failed); }
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