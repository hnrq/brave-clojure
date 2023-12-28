;; 1
(defn attr
  [attribute]
  #(get % attribute))

;; 2
;; (defn custom-comp
;;   "Custom comp implementation"
;;   ([func] (fn [& args] (apply func args)))
;;   ([func & functions]
;;    (fn [& args]
;;      (func (apply (apply custom-comp functions) args)))))
(defn ccomp
  "Custom comp implementation"
  [& fns]
  (reduce (fn [acc, func] #(acc (apply func %&))) fns))

;; 3
(defn cassoc-in
  "Custom assoc-in implementation"
  [map [key & keys] value]
  (if (empty? keys)
    (assoc map key value)
    (assoc map key (cassoc-in (get map key) keys value))))


;; 4
(def characters [{ :intelligence 10 :strength 5 :agility 7} {:intelligence 2 :strength 5 :agility 10}])
(update-in characters [1 :intelligence] inc)

;; 5
(defn cupdate-in
  "Custom update-in implementation"
  [map [key & keys] func & args]
  (if (empty? keys)
    (assoc map key (apply func (concat args [(get map key)])))
    (assoc map key (apply cupdate-in (get map key) keys func args))))
