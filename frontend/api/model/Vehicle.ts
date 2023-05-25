export interface Vehicle {
    id: number,
    brand: string,
    model: string,
    insuranceOffers: [{
        id: number,
        insurer: string,
        price: string,
        vehicleId: number
    }]
}
