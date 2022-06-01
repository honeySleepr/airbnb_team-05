//
//  FavoriteSpotViewDataSource.swift
//  Airbnb
//
//  Created by Jason on 2022/06/01.
//

import UIKit

final class FavoriteSpotViewDataSource: NSObject, UICollectionViewDataSource {
    
    var wishlistData: [MyWishlistSection] = [
        .tumbnailSection([MyWishlistSection.tumbnailItem.init(image: UIImage(named: "tumbnail") ?? UIImage())] ),
        .informationSection([MyWishlistSection.informationItem.init(score: "4.96", review: "(후기 50개)", lodging: "비담집, 비우고담은집(집전체, 최대4인)", price: "₩308,571 / 박")])
    ]
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return wishlistData.count
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        switch wishlistData[section] {
        case let .tumbnailSection(items):
            return items.count
        case let .informationSection(items):
            return items.count
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        switch wishlistData[indexPath.section] {
        
        case let .tumbnailSection(items):
            guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: TumbnailCell.identifier,
                                                                for: indexPath) as? TumbnailCell else {
                return UICollectionViewCell()
            }
            cell.configure(image: items[indexPath.item].image)
            return cell
            
        case let .informationSection(items):
            guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: InformationCell.identifier,
                                                                for: indexPath) as? InformationCell else {
                return UICollectionViewCell()
            }
            let item = items[indexPath.item]
            cell.configure(score: item.score, review: item.review, lodging: item.lodging, price: item.price)
            return cell
        }
    }
    
    enum MyWishlistSection {
        case tumbnailSection([tumbnailItem])
        case informationSection([informationItem])
        
        struct tumbnailItem {
            let image: UIImage
        }
        
        struct informationItem {
            let score: String
            let review: String
            let lodging: String
            let price: String
        }
    }
    enum SectionList: Int, CaseIterable {
        case otherwise = 0
        case tumbnail
        case information
    }
}
