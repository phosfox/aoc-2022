(ns aoc-2022.puzzles.day03
  (:require clojure.set
            [clojure.string :as str]))

(def test-input
  "vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw")

(def input (slurp "resources/day03.txt"))

(defn bisect [ls]
  (split-at (/ (count ls) 2) ls))

(defn find-common-item [left right]
  (clojure.set/intersection left right))

(def priority-of
  (zipmap "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
          (range 1 53)))

(defn part-1 [input]
  (->> input
       str/split-lines
       (map bisect)
       (map (fn [[left right]]
              (clojure.set/intersection (set left) (set right))))
       (map first)
       (map priority-of)
       (reduce +)))

(defn part-2 [input]
  (->> input
       str/split-lines
       (partition 3)
       (map (partial map set))
       (map #(apply clojure.set/intersection %))
       (map first)
       (map priority-of)
       (reduce +)))


(comment
  (part-2 input)
  (find-common-item #{1 2 3} #{4 2 5})
  priority-of
  (apply clojure.set/intersection [#{1 2} #{2 3} #{2 5}])
  (range (int 1) (int 26))
  (part-1 input)
  (part-1 test-input))