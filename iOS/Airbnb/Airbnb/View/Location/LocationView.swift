//
//  LocationView.swift
//  Airbnb
//
//  Created by Jason on 2022/06/10.
//

import UIKit

final class LocationView: UITableView {
    
    override init(frame: CGRect, style: UITableView.Style) {
        super.init(frame: frame, style: .grouped)
        setupLocationTableView()
        setupLocationViewLayout()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError()
    }
    
    private let tableView: UITableView = {
        let tableView = UITableView()
        tableView.backgroundColor = .yellow
        return tableView
    }()
    
    func setupLocationTableView() {
        tableView.register(LocationViewCell.self, forCellReuseIdentifier: LocationViewCell.identifier)
        tableView.separatorStyle = .none
    }
    
    func setupLocationViewLayout() {
        addSubview(tableView)
        tableView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            tableView.topAnchor.constraint(equalTo: safeAreaLayoutGuide.topAnchor),
            tableView.leadingAnchor.constraint(equalTo: safeAreaLayoutGuide.leadingAnchor),
            tableView.trailingAnchor.constraint(equalTo: safeAreaLayoutGuide.trailingAnchor),
            tableView.bottomAnchor.constraint(equalTo: safeAreaLayoutGuide.bottomAnchor)
        ])
    }
}
