(ns supermarket-pricing.core-test
  (:require [clojure.test :refer :all]
            [supermarket-pricing.core :as spc]))

(defn close?
  "Determines if `actual` is within `tolerance` of `expected`."
  [expected actual tolerance]
  (< (Math/abs (- expected actual)) tolerance))

(deftest bean-pricing-test
  (testing "bean pricing"
    (are [expected unit quantity] (= expected (spc/price-beans unit quantity))
         0 0 1
         (float 0) 0.65 0
         0.65 0.65 1)
    (is (close? 1.95 (spc/price-beans 0.65 3) 1e-6))))

(deftest three-for-a-dollar-test
  (testing "three for a dollar"
    (are [expected quantity]
         (= expected (spc/three-for-a-dollar quantity))
         1 3
         0 0
         (/ 4 3) 4
         (/ 5 3) 5
         2 6))
  (testing "three for a dollar with minimum"
    (are [expected quantity]
         (= expected (spc/three-for-a-dollar-with-minimum quantity))
         1 3
         0.0 0
         (/ 4 3) 4
         2 6
         0.80 2)))

(deftest price-per-pound-test
  (testing "price per pound"
    (is (= 1.99 (spc/price-per-pound 1)))
    (is (= 3.98 (spc/price-per-pound 2)))
    (is (close? 0.50 (spc/price-per-pound 0.25) 1e-2))))

(deftest buy-two-get-one-free
  (testing "buy two get one free"
    (are [expected unit-price quantity]
      (= expected (spc/buy-two-get-one-free unit-price quantity))
      0.87 0.87 1
      1.74 0.87 2
      1.74 0.87 3)))



