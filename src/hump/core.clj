(ns hump.core
  (:gen-class)
  (:require [hump.deploy :as deploy]
            [hump.tasks :as tasks]
            [hump.stages :as stages]))

(defn -main
  [stage task]
  (try
    (do
      (let [config (or (System/getenv "HUMP_CONIFG") "./deploy.clj")]
        (println (str "Loading configuration from " config))
        (load-file config)
        (deploy/start (stages/get-stage (keyword stage)) (tasks/get-task task))))
    (catch Exception e (str "Caught exception " (.getMessage e)))))
