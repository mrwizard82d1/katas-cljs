(ns supermarket-pricing.core)

(defn price-beans
  "Calculate the extended price of `quantity` beans with `unit-price`"
  [unit-price quantity]
  (* unit-price quantity))
