(ns supermarket-pricing.core-test
  (:require [clojure.test :refer :all]
            [supermarket-pricing.core :as spc]))

(defn close?
  "Determines if `actual` is within `tolerance` of `expected`."
  [expected actual tolerance]
  (< (Math/abs (- expected actual)) tolerance))

(deftest bean-pricing-test
  (testing "bean pricing"
    (is (= 0 (spc/price-beans 0 1)))
    (is (= (float 0) (spc/price-beans 0.65 0)))
    (is (= 0.65 (spc/price-beans 0.65 1)))
    (is (close? 1.95 (spc/price-beans 0.65 3) 1e-6))))

(deftest three-for-a-dollar-test
  (testing "three for a dollar"
    (is (= 1 (spc/three-for-a-dollar 3)))
    (is (= 0 (spc/three-for-a-dollar 0)))))
