(ns hump.core
  (:gen-class))

(def sites (atom {}))

(def default-props
  {:repository nil
   :branch "master"
   :servers []
   :deploy_to nil})

(defmacro defsite [sitename props]
  `(swap! sites assoc (keyword '~sitename) (merge default-props ~props)))

(defn deploy []
  (println "Nothing to do yet..."))

(defn -main
  [config]
  (println (str "Loading configuration from " config))
  (try
    (do
      (load-file config)
      (deploy))
    (catch Exception e (str "Caught exception " (.getMessage e)))))
