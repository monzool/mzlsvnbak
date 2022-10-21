(ns mzlsvnbak.app
  (:require [babashka.cli :as cli]))


(defn add [m]
  (let [args (:args m)]
    (dorun (map (fn [value] (println "Arg:" value)) args)))
  (println "Add:" (assoc m :fn :add)))

(defn rename [m]
  (println "Rename:" (assoc m :fn :rename)))

(defn delete [m]
  (println "Delete:" (assoc m :fn :delete)))

(defn backup [m]
  (println "Backup:" (assoc m :fn :backup)))

(defn help [m]
  (println "Help:" (assoc m :fn :help)))

(def table
  [{:cmds ["add"]    :fn add}
   {:cmds ["rename"] :fn rename :args->opts [:from :to]}
   {:cmds ["move"]   :fn rename :args->opts [:from :to]}
   {:cmds ["mv"]     :fn rename :args->opts [:from :to]}
   {:cmds ["delete"] :fn delete}
   {:cmds ["remove"] :fn delete}
   {:cmds ["rm"]     :fn delete}
   {:cmds ["backup"] :fn backup}
   {:cmds []         :fn help    :args->opts [:cmd :help-to]}])


(defn -main [& args]
  (println "mzlsvnbak")
  (cli/dispatch table args {:coerce {:depth :long}}))
