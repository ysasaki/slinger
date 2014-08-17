(ns slinger.test.tasks
  (:require [clojure.test :refer :all]
            [slinger.tasks :as tasks]))

(deftest manupilates-tasks
  (testing "get-task"
    (tasks/reset-tasks!)
    (is (= (tasks/get-task :starting) [])))

  (testing "deftask"
    (let [as #(identity "as")
          before #(identity "before")
          after #(identity "after")]
      (tasks/reset-tasks!)
      (tasks/deftask as :as :starting as)
      (tasks/deftask before :before :starting before)
      (tasks/deftask after :after :starting after)
      (is
       (=
        (tasks/get-task :starting)
        [{:before before}
         {:as as}
         {:after after}])))))
