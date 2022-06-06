//
//  ReservationDataSource.swift
//  Airbnb
//
//  Created by Jason on 2022/06/03.
//

import UIKit

final class ReservationDataSource: NSObject, UICollectionViewDataSource {
    
    var reservationData: [MyReservationSection] = [.reservationSection([MyReservationSection.reservationItem.init(image: UIImage(named: "reservation") ?? UIImage(), date: "2021년 5월 17일 - 2021년 6월 4일", location: "서초구, 서울, 한국", description: "Spacious and Comfortable cozy house #4")]),
                                                   .checkInOutSection([MyReservationSection.checkInOutItem.init(checkInDate: "2021년 5월 17일 오후 4:00", checkOutDate: "2021년 6월 4일 오후 12:00")]),
                                                   .summaryInfoSection([MyReservationSection.summaryInfoItem.init(hostName: "호스트: Jong님", guestCount: "집전체∙게스트 3명", totalPrice: "₩1,488,195")])
    ]
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return reservationData.count
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        switch reservationData[section] {
        case let .reservationSection(items):
            return items.count
        case let .checkInOutSection(items):
            return items.count
        case let .summaryInfoSection(items):
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
            
        case let .checkInOutSection(items):
            guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: CheckInOutCell.identifier, for: indexPath) as? CheckInOutCell else {
                return UICollectionViewCell()
            }
            let item = items[indexPath.item]
            cell.configure(InDate: item.checkInDate, OutDate: item.checkOutDate)
            return cell
            
        case let .summaryInfoSection(items):
            guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: SummaryInfoCell.identifier, for: indexPath) as? SummaryInfoCell else {
                return UICollectionViewCell()
            }
            let item = items[indexPath.item]
            cell.configure(name: item.hostName, guestcnt: item.guestCount, price: item.totalPrice)
            return cell
        }
    }
    
    enum MyReservationSection {
        case reservationSection([reservationItem])
        case checkInOutSection([checkInOutItem])
        case summaryInfoSection([summaryInfoItem])
        
        struct reservationItem {
            let image: UIImage
            let date: String
            let location: String
            let description: String
        }
        
        struct checkInOutItem {
            let checkInDate: String
            let checkOutDate: String
        }
        
        struct summaryInfoItem {
            let hostName: String
            let guestCount: String
            let totalPrice: String
        }
    }
    
}
