//
//  SearchViewDataSource.swift
//  Airbnb
//
//  Created by Jason on 2022/05/27.
//

import UIKit

final class HomeViewDataSource: NSObject, UICollectionViewDataSource {
    
    var homeData: [MyHomeSection] = [
        .advertising([MyHomeSection.advertisingItem.init(image: UIImage(named: "mainImage") ?? UIImage())] ),
        .destination([MyHomeSection.destinationItem].init(repeating: MyHomeSection.destinationItem.init(image: UIImage(named: "seoul") ?? UIImage(),
                                                                                                        title: "서울", distance: "차로 30분 거리"), count: 20)),
        .livingSpot([MyHomeSection.livingSpotItem].init(repeating: MyHomeSection.livingSpotItem.init(image: UIImage(named: "blackHouse") ?? UIImage(), title: "자연생활을 만끽할 수 있는 숙소"), count: 5))
    ]
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return homeData.count
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        switch homeData[section] {
        case let .advertising(items):
            return items.count
        case let .destination(items):
            return items.count
        case let .livingSpot(items):
            return items.count
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        switch homeData[indexPath.section] {
        case let .advertising(items):
            guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: AdvertisingCell.identifier,
                                                                for: indexPath) as? AdvertisingCell else {
                return UICollectionViewCell()
            }
            cell.configure(image: items[indexPath.item].image)
            
            return cell
        case let .destination(items):
            guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: NearbyDestinationCell.identifier,
                                                                for: indexPath) as? NearbyDestinationCell else {
                return UICollectionViewCell()
            }
            let item = items[indexPath.item]
            cell.configure(image: item.image, title: item.title, distance: item.distance)
            
            return cell
        case let .livingSpot(items):
            guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: LivingSpotCell.identifier,
                                                                for: indexPath) as? LivingSpotCell else {
                return UICollectionViewCell()
            }
            let item = items[indexPath.item]
            cell.configure(image: item.image, title: item.title)
            
            return cell
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {
        switch kind {
        case UICollectionView.elementKindSectionHeader:
            guard let headerView = collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: CollectionHeaderView.identifier, for: indexPath) as? CollectionHeaderView else {
                return UICollectionReusableView()
            }
            
            let sectionType = SectionList.allCases[indexPath.section]
            switch sectionType {
            case .otherwise:
                headerView.setHeaderText(text: "")
            case .destination:
                headerView.setHeaderText(text: "가까운 여행지 둘러보기")
                headerView.setHeaderFontSize(size: 22)
            case .livingSpot:
                headerView.setHeaderText(text: "어디에서나,\n여행은 살아보는거야!")
                headerView.setHeaderFontSize(size: 22)
            }
            
            return headerView
        default:
            return UICollectionReusableView()
        }
    }
    
    enum MyHomeSection {
        case advertising([advertisingItem])
        case destination([destinationItem])
        case livingSpot([livingSpotItem])
        
        struct advertisingItem {
            let image: UIImage
        }
        
        struct destinationItem {
            let image: UIImage
            let title: String
            let distance: String
        }
        
        struct livingSpotItem {
            let image: UIImage
            let title: String
        }
    }
    enum SectionList: Int, CaseIterable {
        case otherwise = 0
        case destination
        case livingSpot
    }
}
