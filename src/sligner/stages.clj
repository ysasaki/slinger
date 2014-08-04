(ns slinger.stages)

(def stages (atom {}))

(defmacro defstage [stage props]
  `(swap! stages assoc (keyword '~stage) ~props))
