(ns aoc-2022.puzzles.day05 
  (:require [clojure.string :as str]))


(defn parse-int [n]
  (Integer/parseInt n))

(def test-crates
  "    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 ")

(def test-rearrangement-procedure
  "move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2")

(defn line->procedure [line]
  (let [[amount from to] (map parse-int (re-seq #"\d+" line))]
       {:amount amount :from from :to to }))

(defn parse-procedures [input]
  (->>
   (str/split-lines input)
   (map line->procedure)))

(comment
  (parse-procedures test-rearrangement-procedure)
  (let [lines (reverse (str/split-lines test-crates))
         stacks (drop 1 lines)]
    [stacks])
  (last (str/split-lines test-crates))
  )