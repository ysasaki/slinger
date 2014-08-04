(ns slinger.sites)

(def sites (atom {}))

(def default-site-props
  {:repository nil
   :branch "master"
   :servers []
   :deploy_to nil})

(defn get-stage [keyword] (keyword @sites))

(defmacro defsite [sitename props]
  `(swap! sites assoc (keyword '~sitename) (merge default-site-props ~props)))
