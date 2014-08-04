(ns slinger.tasks)

(def tasks (atom {:setup []
                  :update-code []
                  :finalize-update []
                  :symlink []
                  :restart []}))

(defn get-task [k] (k @tasks))

(defn- set-task! [k v1 v2]
  (swap! tasks assoc k (into v1 v2)))

(defmacro deftask
  ([task k f]
   `(set-task! ~k [] [{(keyword '~task) ~f}]))
  ([task pos k f]
   `(cond
     (= ~pos :before) (set-task! ~k [{(keyword '~task) ~f}] (get-task ~k))
     (= ~pos :after) (set-task! ~k (get-task ~k) [{(keyword '~task) ~f}])
     :else (deftask ~task ~k ~f))))
