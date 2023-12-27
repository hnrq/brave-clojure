(def filename "suspects.csv")

(def vamp-keys [:name :glitter-index])
(defn str->int [str] (Integer. str))
(def conversions {:name identity :glitter-index str->int })

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value)
)

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
        (clojure.string/split string #"\n"))
)

(defn mapify
  "Return a seq of maps"
  [rows]
  (map (fn [row] 
          (reduce (fn [row-map [vamp-key value]]
            (assoc row-map vamp-key (convert vamp-key value)))
            {}
            (map vector vamp-keys row)))
    rows)
)

(defn glitter-filter [min records]
  (filter #(>= (:glitter-index %) min) records))


(def records (mapify (parse (slurp filename))))
(def filtered-records (glitter-filter 3 records))

;; 1
(println (map #(:name %) filtered-records))

;; 2
(defn append
  "Appends a new suspect to the list"
  [records entry]
  (conj records entry)
)

;; 3
(def suspect-validations {:name string? :glitter-index integer? })

(defn validate
  "Validates a map content"
  [validations record]
  (every? true? (map (fn [[key validation-fn]] (validation-fn (key record))) validations)))
(println (validate suspect-validations (nth records 1)))

;; 4
(defn to-csv
  [data]
  (clojure.string/join "\n" (map #(clojure.string/join "," (vals %)) data))
)

(println (to-csv (append records {:name "Ayrton Senna" :glitter-index 10})))