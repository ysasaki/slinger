(ns slinger.core
  (:gen-class)
  (:require [slinger.deploy :as deploy]
            [slinger.tasks :as tasks]
            [slinger.stages :as stages]))

(defn -main
  [stage task]
  (try
    (do
      (let [config (or (System/getenv "HUMP_CONIFG") "./deploy.clj")]
        (println (str "Loading configuration from " config))
        (load-file config)
        (deploy/start (stages/get-stage (keyword stage)) (tasks/get-task task))))
    (catch Exception e (str "Caught exception " (.getMessage e)))))
