(ns slinger.tasks
  (:require [clojure.tools.logging :as log]))

(def tasks
  "Global task informations."
  (atom {:starting [] :updating [] :publishing [] :finishing [] }))

(defn reset-tasks!
  "Resets all task infomations."
  []
  (reset! tasks {:starting [] :updating [] :publishing [] :finishing []}))

(defn get-task
  "Gets task information from tasks by key."
  [k]
  (k @tasks))

(defn set-task!
  "Sets task information. You must use deftask instead of this function."
  [k v1 v2]
  (swap! tasks assoc k (into v1 v2)))

(defmacro deftask
  "Adds task definition to global tasks."
  [task pos seq-pos f]
  `(cond
    (= ~pos :before) (set-task! ~seq-pos [{(keyword '~task) ~f}] (get-task ~seq-pos))
    (= ~pos :after) (set-task! ~seq-pos (get-task ~seq-pos) [{(keyword '~task) ~f}])
    (= ~pos :as) (set-task! ~seq-pos [] [{(keyword '~task) ~f}])
    :else (log/error "You must choose at least one position prefix from :before, :after, :as.")))
