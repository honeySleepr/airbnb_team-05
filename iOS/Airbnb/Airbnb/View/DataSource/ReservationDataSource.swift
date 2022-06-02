//
//  ReservationDataSource.swift
//  Airbnb
//
//  Created by Jason on 2022/06/03.
//

import UIKit

final class ReservationDataSource: NSObject, UICollectionViewDataSource {
    
    var reservationData: [MyReservationSection] = [.reservationSection([MyReservationSection.reservationItem.init(image: UIImage(named: "reservation") ?? UIImage(), date: "2021년 5월 17일 - 2021년 6월 4일", location: "서초구, 서울, 한국", description: "Spacious and Comfortable cozy house #4")])
    ]
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return reservationData.count
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        switch reservationData[section] {
        case let .reservationSection(items):
            return items.count
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        switch reservationData[indexPath.section] {
        case let .reservationSection(items):
            guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: ReservationCell.identifier, for: indexPath) as? ReservationCell else {
                return UICollectionViewCell()
            }
            let item = items[indexPath.item]
            cell.configure(image: item.image, date: item.date, location: item.location, description: item.description)
            return cell
        }
    }
    
    enum MyReservationSection {
        case reservationSection([reservationItem])
        
        struct reservationItem {
            let image: UIImage
            let date: String
            let location: String
            let description: String
        }
    }
    
}
