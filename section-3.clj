(ns cftbat.core)

(def name "Ayrton Senna")

;; 1
(println (str "Hello, " name))

;; 2
(defn add100 [num] (+ 100 num))
(println (add100 5))

;; 3
(defn dec-maker [num] #(- % num))
(println((dec-maker 9) 10))

;; 4
(defn mapset 
  "A map that returns a set"
  [fn seq]
  (into #{} (map fn seq))
)
(println (mapset inc [1 1 2 2]))