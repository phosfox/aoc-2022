(ns aoc-2022.puzzles.day04
  (:require clojure.set
            [clojure.string :as str]))

(def test-input
  "2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8")

(defn parse-int [n]
  (Integer/parseInt n))

(defn parse [input]
  (->> (str/split-lines input)
       (map (fn [line] (str/split line #",")))
       (map (fn [[left right]] [(str/split left #"-") (str/split right #"-")]))
       (map flatten)
       (map (fn [nn] (map parse-int nn)))
       (map (fn [nn] (partition 2 nn)))))

(parse test-input)

(defn fully-contains [[[left-start left-end] [right-start right-end]]]
  (or (<= left-start right-start right-end left-end)
      (<= right-start left-start left-end right-end)))

(defn overlaps? [[[left-start left-end] [right-start right-end]]]
  (or (<= left-start right-start left-end)
      (<= right-start left-start right-end)))

(comment
  (overlaps? '((2 4) (6 8)))
  (overlaps? '((2 3) (4 5)))
  (overlaps? '((5 7) (7 9)))
  (overlaps? '((2 8) (3 7)))
  (overlaps? '((6 6) (4 6)))
  (overlaps? '((2 6) (4 8)))
  )

(defn solve [input section-overlap]
  (let [fully-contained (->> (parse input)
       (map section-overlap)
       frequencies
       )]
  (get fully-contained true)))

(def input (slurp "resources/day04.txt"))

(solve input fully-contains)

(solve input overlaps?)