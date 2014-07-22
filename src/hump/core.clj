(ns hump.core
  (:gen-class)
  (:require [hump.deploy :as deploy]))

(def sites (atom {}))
(def stages (atom {}))

(def default-site-props
  {:repository nil
   :branch "master"
   :servers []
   :deploy_to nil})

(defmacro defsite [sitename props]
  `(swap! sites assoc (keyword '~sitename) (merge default-site-props ~props)))

(defmacro defstage [stage props]
  `(swap! stages assoc (keyword '~stage) ~props))

(defn -main
  [stage task]
  (try
    (do
      (let [config (or (System/getenv "HUMP_CONIFG") "./deploy.clj")]
        (println (str "Loading configuration from " config))
        (load-file config)
        (deploy/start @sites @stage)))
    (catch Exception e (str "Caught exception " (.getMessage e)))))
