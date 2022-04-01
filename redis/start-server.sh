
for d in ./*/ ; do (cd "$d" && redis-server ./redis.conf &); done


redis-cli --cluster create 127.0.0.1:8000 127.0.0.1:8001 \
127.0.0.1:8002 127.0.0.1:8003 127.0.0.1:8004 127.0.0.1:8005 \
--cluster-replicas 1