use std::collections::HashMap;
use std::fs::File;
use std::io::Read;
use std::path::Path;
use crate::problem0001::problem0001;
use crate::problem0002::problem0002;
use crate::problem0003::problem0003;
use crate::problem0004::problem0004;
use crate::problem0005::problem0005;
use crate::problem0006::problem0006;

mod problem0001;
mod problem0002;
mod utils;
mod problem0003;
mod problem0004;
mod problem0005;
mod problem0006;

fn main() {
    // wow
    // this will need some tidying

    let mut solutions: HashMap<usize, &str> = HashMap::new();
    let solutions_path = Path::new("../solutions.csv");
    let mut file = File::open(solutions_path).expect("Could not find solutions file.");
    let mut contents = String::new();
    file.read_to_string(&mut contents).expect("Unable to read solutions file.");
    let lines = contents.lines();
    for line in lines {
        let bits: Vec<&str> = line.split(',').collect();
        let n: &str = bits.get(0).expect("Could not read problem number").trim();
        let s: &str = bits.get(1).expect("Could not read problem solution").trim();
        solutions.insert(n[1..n.len()-1].parse().expect("Could not parse problem number"), &s[1..s.len()-1]);
    }


    let mut problems: HashMap<usize, fn() -> String> = HashMap::new();
    problems.insert(1, problem0001);
    problems.insert(2, problem0002);
    problems.insert(3, problem0003);
    problems.insert(4, problem0004);
    problems.insert(5, problem0005);
    problems.insert(600, problem0006);

    println!("Project Euler (rust edition)");
    println!("{} problem(s) have been solved.", problems.len());
    println!();

    let largest = problems.keys().max().unwrap();
    for num in 1..=*largest {
        let solver = match problems.get(&num) {
            None => continue,
            Some(solver) => solver
        };
        use std::time::Instant;
        let now = Instant::now();
        let actual = solver();
        let elapsed = now.elapsed();
        let expected = match solutions.get(&num) {
            None => "Unknown",
            Some(s) => if actual.eq(s) { "Pass" } else { "Fail" }
        };
        println!("Problem {} (https://projecteuler.net/problem={}): {} in {:.2?} ({})", num, num, actual, elapsed, expected)
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