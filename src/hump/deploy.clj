(ns hump.deploy)


(defn start
  "Starts deployment in the order."
  [sites stages])

(defn setup
  "Setups required directories and files."
  [])

(defn update
  "Update container. Nothing to do."
  [])

(defn update-code
  "Updates application code using `git checkout` or somethings."
  [])

(defn finalize-update
  "Changes permission of copied codes."
  [])

(defn symlink
  "Symlinks current to latest release codes."
  [])

(defn restart
  "Restarts an application server."
  [])

(def deploy-order
  '(setup update update-code finalize-update symlink restart))
