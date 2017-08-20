(ns supermarket-pricing.core)

(defn simple-price
      "Calculate the extended price using a simple model: `quantity` * `unit-price`."
      [unit-price quantity]
      (* unit-price quantity))

(defn price-beans
  "Calculate the extended price of `quantity` beans with `unit-price`"
  [unit-price quantity]
  (simple-price unit-price quantity))

(defn three-for-a-dollar
  "Calculate the extended price of `quantity` priced at 3 for a dollar."
  [quantity]
  (simple-price (/ 1 3) quantity))

(defn three-for-a-dollar-with-minimum
  "Calculate the extended price of `quantity` priced at 3 for a dollar, regularly 0.40, minimum of 3."
  [quantity]
  (if (>= quantity 3)
    (three-for-a-dollar quantity)
    (simple-price 0.40 quantity)))
