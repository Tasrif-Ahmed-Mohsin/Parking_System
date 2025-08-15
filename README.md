<p align="center">
  <img src="https://github.com/user-attachments/assets/ba01bc0f-34e4-4c5a-889f-9c2b280c44d7" width="400"/>
  <img src="https://github.com/user-attachments/assets/23501119-ba8a-4181-89b8-2e06fba8ce24" width="400"/>
</p>


### ✅ **Core Features** 

1. **Parking Spot Management**

   * A fixed number (`5`) of parking spots (`TOTAL_SPOTS`).
   * Each spot can be **available** or **occupied**.
   * Real-time status update in a table.

2. **Car Management**

   * `Car` object stores `licensePlate` and `entryTime`.
   * Cars can be parked in the **first available spot**.
   * License plate is required to identify and remove cars.

3. **Fee Calculation**

   * Parking fee is calculated based on **time parked** (`$0.005 per second`).
   * Displayed when a car is removed.

---

### 🧰 **Technical Highlights**

* **Dynamic Table Update**:
  Uses `DefaultTableModel` to refresh parking status.

* **Event Handling**:
  `ActionListener` for `Park Car` and `Remove Car` buttons.

* **Input Validation**:
  Warns the user if the license plate field is empty.

* **Simple Object-Oriented Design**:
  Classes: `Car`, `ParkingSpot`, and `SmartParkingGUI`.

---
#STOP
