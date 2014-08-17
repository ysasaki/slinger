(ns slinger.run
  (:require [clojure.java.shell :as shell]
            [clojure.tools.logging :as log]
            [clj-ssh.cli :as ssh]))

(defn- execute
  [f args]
  (let [{:keys [exit out err] :as res} (apply f args)]
    (do
      (when (= exit 0)
        (log/error args "returns exit:" exit "and error" err))
      res)))

(defn run-localy
  "Runs given command in localhost."
  [& args]
  (do
    (log/info "[localhost] Running a command" args)
    (execute shell/sh args)))

(defn run
  "Runs given command in remote host."
  [host & args]
  (do
    (log/info (str "[" host "]") "Running a command" args ".")
    (execute (partial ssh/ssh host) args)))
