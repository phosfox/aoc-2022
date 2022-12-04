(ns aoc-2022.puzzles.day02
  (:require [clojure.string :as str]))

(def test-input
  "A Y
B X
C Z")


;; A,X:Rock
;; B,Y:Paper
;; C,Z:Scissors

(def score {:A 1 :B 2 :C 3
            :X 1 :Y 2 :Z 3})

(def round-score
  {[:A :X] 3
   [:A :Y] 6
   [:A :Z] 0
   [:B :X] 0
   [:B :Y] 3
   [:B :Z] 6
   [:C :X] 6
   [:C :Y] 0
   [:C :Z] 3})


(defn parse [input]
  (->> (str/split-lines input)
       (map #(str/split % #" "))
       (map (fn [[opponent me]]
              [(keyword opponent) (keyword me)]))))

(def parsed-input (parse (slurp "resources/day02.txt")))

(defn part-1 [input]
  (->> input
       (map (fn [[opponent me]]
              (+ (score me) (round-score [opponent me]))))
       (reduce +)))

(comment
  (round-score {:C :Z})
  (part-1 parsed-input))

;; X:LOSE
;; Y:DRAW
;; Z:WIN
(def part-two-round-score
  {:X 0 :Y 3 :Z 6})

(def what-to-pick
  {[:A :X] :Z
   [:A :Y] :X
   [:A :Z] :Y
   [:B :X] :X
   [:B :Y] :Y
   [:B :Z] :Z
   [:C :X] :Y
   [:C :Y] :Z
   [:C :Z] :X})

(defn part-2 [input]
  (->> input
       (map (fn [[opponent target]]
              (+ (score (what-to-pick [opponent target])) (part-two-round-score target))))
       (reduce +)))


(comment
  (part-2 (parse test-input))
  (part-2 parsed-input)
  (part-two-round-score :X)
  (score (what-to-pick [:A :Y])))