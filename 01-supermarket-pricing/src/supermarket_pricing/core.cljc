(ns supermarket-pricing.core)

(defn price-beans
  "Calculate the extended price of `quantity` beans with `unit-price`"
  [unit-price quantity]
  (* unit-price quantity))

(defn three-for-a-dollar
  "Calculate the extended price of `quantity` priced at 3 for a dollar."
  [quantity]
  (price-beans (/ 1 3) quantity))
