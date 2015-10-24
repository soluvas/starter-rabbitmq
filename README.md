# Starter RabbitMQ

Starter project with JSON-LD and RabbitMQ Support

## Cara Test

1. Buka web UI aplikasi ini di [http://localhost:8080/](http://localhost:8080/)
    Awalnya aplikasi hanya memiliki satu data yaitu **ITB**.
    Tetap biarkan aplikasi terbuka, tidak perlu diclose.
2. Enable plugin `rabbitmq_management`. Caranya: Jalankan **RabbitMQ Command Prompt**, lalu ketik:

        rabbitmq-plugins enable rabbitmq_management
        
2. Pastikan Anda bisa akses [http://localhost:15672/](http://localhost:15672/) (user: guest, password: guest)
3. Klik tab **Exchanges**
4. Klik exchange **amq.topic**
5. Masuk bagian **Publish message**, masukkan:

    Routing key: `starter.place`

    Payload:
    
        {
            "@type": "Place",
            "name": "Warung Mamah",
            "description": "Enak top markotop"
        }

    Klik **Publish message**.
6. Cek di web UI aplikasi bahwa place **Warung Mamah** telah ditambahkan.
