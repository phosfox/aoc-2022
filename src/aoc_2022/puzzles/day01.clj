(ns aoc-2022.puzzles.day01

  (:require [clojure.string :as str]))


(defn parse-int [n]
  (Integer/parseInt n))

(def input (slurp "resources/day01.txt"))


(defn part-1 [input]
  (->> (str/split input #"\n\n")
       (map str/split-lines)
       (map #(map parse-int %))
       (map #(reduce + %))
       (apply max)))

(time
 (dotimes [_ 10000]
   (part-1 input)))


(defn part-2 [input]
  (->> (str/split input #"\n\n")
       (map str/split-lines)
       (map #(map parse-int %))
       (map #(reduce + %))
       (sort >)
       (take 3)
       (reduce +)))

(part-2 input)
