-- vehicles
SELECT v.id    AS vehicle_id,
       v.brand AS vehicle_brand,
       v.model AS vehicle_model
FROM vehicles v
         INNER JOIN users u ON u.login = v.login
WHERE u.id = -1
ORDER BY v.insert_time;


-- offers
SELECT id, insurer, price
FROM insurance_offers
WHERE vehicle_id IN (-1, -2, -3, 666, -666)
