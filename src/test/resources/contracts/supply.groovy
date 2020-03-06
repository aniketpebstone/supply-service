package contracts

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            request {
                name "Should get tires for a given model"
                method GET()
                url $(consumer('/v1/supply/ModelX/summer'), producer('/v1/supply/ModelX/summer'))
            }
            response {
                status 200
                body([
                        "model": "ModelX", "season": "summer", "tireModel": "stealth", "price": "1000", "deliveryDate": "01012018"
                ])
                headers {
                    contentType("application/json")
                }
            }
        }

]
