//
//  TabBarController.swift
//  Airbnb
//
//  Created by Jason on 2022/05/26.
//

import UIKit

class TabBarController: UITabBarController {
    
    let homeViewController = HomeViewController()
    let wishlistViewController = WishlistViewController()
    let reservationViewController = ReservationViewController()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setTabBarItems()
        setTabBarBackgroundColor()
    }
}

private extension TabBarController {
    
    func setTabBarItems() {
        let navigationViewController = UINavigationController(rootViewController: homeViewController)
        
        navigationViewController.tabBarItem = UITabBarItem(title: "검색", image: UIImage(systemName: "magnifyingglass"), tag: 0)
        wishlistViewController.tabBarItem = UITabBarItem(title: "위시리스트", image: UIImage(systemName: "heart"), tag: 1)
        reservationViewController.tabBarItem = UITabBarItem(title: "내 예약", image: UIImage(systemName: "person.fill"), tag: 2)
        self.viewControllers = [navigationViewController, wishlistViewController, reservationViewController]
    }
    
    func setTabBarBackgroundColor() {
        if #available(iOS 15.0, *) {
            let appearance = UITabBarAppearance()
            appearance.configureWithOpaqueBackground()
            appearance.backgroundColor = .systemGray6
            self.tabBar.standardAppearance = appearance
            self.tabBar.scrollEdgeAppearance = self.tabBar.standardAppearance
        } else {
            self.tabBar.barTintColor = .systemGray6
        }
    }
}
